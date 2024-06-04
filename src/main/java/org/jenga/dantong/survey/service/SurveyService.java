package org.jenga.dantong.survey.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.util.Util;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.post.repository.PostRepository;
import org.jenga.dantong.survey.exception.AlreadyHasSurveyException;
import org.jenga.dantong.survey.exception.SurveyNotFoundException;
import org.jenga.dantong.survey.model.dto.SurveyCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyItemCreateRequest;
import org.jenga.dantong.survey.model.dto.SurveyItemResponse;
import org.jenga.dantong.survey.model.dto.SurveyItemUpdateRequest;
import org.jenga.dantong.survey.model.dto.SurveyResponse;
import org.jenga.dantong.survey.model.dto.SurveyUpdateRequest;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.repository.SurveyItemRepository;
import org.jenga.dantong.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyItemRepository surveyItemRepository;
    private final PostRepository postRepository;

    @Transactional
    public SurveyResponse findSurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
            .orElseThrow(SurveyNotFoundException::new);

        if (!survey.isShown()) {
            return SurveyResponse.builder()
                .description("Deleted Survey")
                .build();
        }

        List<SurveyItem> items = surveyItemRepository.findBySurvey_SurveyIdAndShownTrue(surveyId);

        List<SurveyItemResponse> responseItems = items.stream()
            .map(currItem -> SurveyItemResponse.builder()
                .surveyItemId(currItem.getSurveyItemId())
                .title(currItem.getTitle())
                .tag(currItem.getTag())
                .options(currItem.getOptions())
                .build())
            .toList();

        SurveyResponse response = SurveyResponse.builder()
            .title(survey.getTitle())
            .description(survey.getDescription())
            .postId(Objects.isNull(survey.getPost()) ? 0 : survey.getPost().getPostId())
            .startTime(survey.getStartTime())
            .endTime(survey.getEndTime())
            .status(Util.getProgress(survey))
            .surveyItems(responseItems)
            .build();

        return response;
    }

    @Transactional
    public Long create(SurveyCreateRequest surveyCreate) {
        Post post = postRepository.findByPostId(surveyCreate.getPostId());
        if (post.hasSurvey()) {
            throw new AlreadyHasSurveyException();
        }
        Survey survey = new Survey(
            surveyCreate.getTitle(),
            surveyCreate.getDescription(),
            post,
            surveyCreate.getStartTime(),
            surveyCreate.getEndTime()
        );

        surveyRepository.save(survey);

        List<SurveyItemCreateRequest> item = surveyCreate.getSurveyItems();

        item.stream()
            .map(currItem -> SurveyItem.builder()
                .survey(survey)
                .title(currItem.getTitle())
                .tag(currItem.getTag())
                .description(currItem.getDescription())
                .options(currItem.getOptions())
                .build())
            .forEach(surveyItemRepository::save);

        return survey.getSurveyId();
    }

    @Transactional
    public Long updateSurvey(Long surveyId, SurveyUpdateRequest request) {
        Survey survey = surveyRepository.findById(surveyId)
            .orElseThrow(SurveyNotFoundException::new);
        Post post = postRepository.findByPostId(request.getPostId());

        survey.setTitle(request.getTitle());
        survey.setDescription(request.getDescription());
        survey.setPost(post);
        survey.setStartTime(request.getStartTime());
        survey.setEndTime(request.getEndTime());

        List<SurveyItemUpdateRequest> itemUpdate = request.getSurveyItems();

        itemUpdate.stream()
            .filter(currItem ->
                surveyItemRepository.findBySurveyItemId(currItem.getSurveyItemId()) == null || (
                    surveyId.equals(
                        surveyItemRepository.findBySurveyItemId(currItem.getSurveyItemId())
                            .getSurvey().getSurveyId())))
            .forEach(currItem -> {
                SurveyItem item = surveyItemRepository.findBySurveyItemId(
                    currItem.getSurveyItemId());

                if (item != null) {
                    log.info("Item detected");
                    item.setTitle(currItem.getTitle());
                    item.setTag(currItem.getTag());
                    item.getOptions().clear();
                    currItem.getOptions().forEach(option -> {
                        item.getOptions().add(option);
                    });
                } else {
                    log.info("New Item detected");

                    SurveyItem newItem = SurveyItem.builder()
                        .survey(survey)
                        .surveyItemId(currItem.getSurveyItemId())
                        .title(currItem.getTitle())
                        .tag(currItem.getTag())
                        .options(currItem.getOptions())
                        .build();

                    surveyItemRepository.save(newItem);
                }
            });

        return survey.getSurveyId();
    }

    @Transactional
    public void deleteSurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
            .orElseThrow(SurveyNotFoundException::new);

        survey.setShown(false);
    }

    @Transactional
    public void deleteSurveyItem(Long surveyId, Long itemId) {
        SurveyItem item = surveyItemRepository.findBySurvey_SurveyIdAndSurveyItemId(surveyId,
            itemId).orElseThrow();

        item.setShown(false);
    }
}
