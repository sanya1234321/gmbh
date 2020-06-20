package hh.gmbh.db.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CountValueProjection {
    Long count;
    String value;
}
