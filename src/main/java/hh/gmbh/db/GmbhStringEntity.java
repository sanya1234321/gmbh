package hh.gmbh.db;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GMBH_STRING")
@Data
@Builder
public class GmbhStringEntity {

    @Id
    @Column
    private String value;

    @Column
    private Integer length;

}
