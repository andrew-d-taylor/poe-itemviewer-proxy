package com.poeitems.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
public class ProxyResource {

    private static final String PUBLIC_STASHES_URL = "http://api.pathofexile.com/public-stash-tabs";
    private static final String UNIQUES_URL = "http://www.pathofexile.com/api/trade/data/items";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/uniques")
    @ResponseBody
    public Object getUniques() {
        ResponseEntity<Object> response = restTemplate.getForEntity(UNIQUES_URL, Object.class);
        return response.getBody();
    }

    @RequestMapping(path = "/items")
    @ResponseBody
    public Object getItems(@RequestParam(name = "id", required=false) String changeId) {
        String url = !StringUtils.isEmpty(changeId) ? PUBLIC_STASHES_URL + "?id=" + changeId : PUBLIC_STASHES_URL;
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        return response.getBody();
    }

}
