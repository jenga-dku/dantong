package org.jenga.dantong.post;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.post.model.entity.QPost;
import org.springframework.stereotype.Repository;

import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom{
    private JPAQueryFactory jpaQueryFactory;

    public PostRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Post> findRemainPost() {
        QPost post = QPost.post;

        return jpaQueryFactory.selectFrom(post)
                .where(post.shown.eq(true))
                .fetch();
    }

}
