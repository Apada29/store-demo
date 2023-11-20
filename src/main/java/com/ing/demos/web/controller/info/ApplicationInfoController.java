package com.ing.demos.web.controller.info;

import com.ing.demos.web.utils.Utils;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/")
public class ApplicationInfoController {

    private final BuildProperties buildProperties;

    public ApplicationInfoController(Optional<BuildProperties> buildProperties) {
        this.buildProperties = buildProperties.orElse(null);
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, String> getApplicationInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("buildVersion", buildProperties.getVersion());
        info.put("buildDate", Utils.getInstantFormatted(buildProperties.getTime()));
        info.put("buildName", buildProperties.getName());

        return info;
    }

}
