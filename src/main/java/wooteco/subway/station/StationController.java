package wooteco.subway.station;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wooteco.subway.station.dao.CollectionStationDao;

@RestController
public class StationController {

    @Autowired
    private CollectionStationDao collectionStationDao;

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StationResponse> exceptionHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/stations")
    public ResponseEntity<StationResponse> createStation(
        @RequestBody StationRequest stationRequest) {
        Station station = new Station(stationRequest.getName());
        Station newStation = collectionStationDao.save(station);
        StationResponse stationResponse = new StationResponse(newStation.getId(),
            newStation.getName());
        return ResponseEntity.created(URI.create("/stations/" + newStation.getId()))
            .body(stationResponse);
    }

    @GetMapping(value = "/stations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StationResponse>> showStations() {
        List<Station> stations = collectionStationDao.findAll();
        List<StationResponse> stationResponses = stations.stream()
            .map(it -> new StationResponse(it.getId(), it.getName()))
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(stationResponses);
    }

    @DeleteMapping("/stations/{id}")
    public ResponseEntity deleteStation(@PathVariable Long id) {
        collectionStationDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
