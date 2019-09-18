package com.afkl.travel.exercise.controller;

import com.afkl.travel.exercise.persistence.LocationDto;
import com.afkl.travel.exercise.persistence.LocationRepository;
import com.afkl.travel.exercise.persistence.LocationType;
import com.afkl.travel.exercise.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Validated
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    MetricService metricService;


    @RequestMapping(value = "/travel/locations", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public List<LocationDto>  getLocations(Model model) {

        List<LocationDto> locations = locationRepository.findAll().stream().map(l -> LocationDto.fromEntity(l)).
                collect(Collectors.toList());

        model.addAttribute("locations", locations);


        return locations;

    }

    @RequestMapping(value = "/travel/locations/{type}/{code}/", method = RequestMethod.GET, produces = { "application/json" })
    public List<LocationDto> getLocationsByTypeAndCode(Model model, @PathVariable("type") LocationType type,
                                                       @PathVariable("code") String code) {

        List<LocationDto> locations = locationRepository.findAllLocationsByTypeAndCode(type,code).stream().map(l -> LocationDto.fromEntity(l)).
                collect(Collectors.toList());

        model.addAttribute("airports", locations);

        return locations;

    }


    @RequestMapping(value = "/actuator/my-metrics", method = RequestMethod.GET, produces = { "application/json" })
    public Map getMetrics(Model model) {

        Map metrics =  metricService.getMetrics();
        model.addAttribute("metrics", metrics);
        return metrics;

    }

}
