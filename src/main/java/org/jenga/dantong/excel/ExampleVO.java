package org.jenga.dantong.excel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@Table(name = "excel")
@NoArgsConstructor
@AllArgsConstructor
public class ExampleVO {

    @Id
    @GeneratedValue
    private Long id;

    private String column1;

    private String column2;

    private Integer column3;

    private Long column4;

    private Double column5;

    private String column6;

    @Override
    public String toString() {
        return String.join(","
            , "" + this.getId()
            , "" + this.getColumn1()
            , "" + this.getColumn2()
            , "" + this.getColumn3()
            , "" + this.getColumn4()
            , "" + this.getColumn5()
            , "" + this.getColumn6());
    }
}