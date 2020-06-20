package hh.gmbh.api;

import hh.gmbh.db.entities.CountValueProjection;
import hh.gmbh.db.entities.GmbhStringEntity;
import hh.gmbh.db.repo.GmbhStringRepository;
import hh.gmbh.model.GmbhStringRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/strings")
public class StringsController {

    @Autowired
    private GmbhStringRepository gmbhStringRepository;

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody GmbhStringRequest gmbhStringRequest) {

        GmbhStringEntity entity = GmbhStringEntity.builder()
                                                    .value(gmbhStringRequest.getValue())
                                                    .length(gmbhStringRequest.getValue().length())
                                                    .build();

        GmbhStringEntity saved = gmbhStringRepository.save(entity);

        return ok(saved.getValue());
    }

    @GetMapping("/count")
    public ResponseEntity<List<CountValueProjection>> getCount() {
        return ok(gmbhStringRepository.countStrings());
    }

    @GetMapping("/avg")
    public ResponseEntity<Integer> getAverageLength() {
        return ok(gmbhStringRepository.getAverageLength());
    }


}
