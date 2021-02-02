package one.goranson.bokker.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import one.goranson.bokker.service.AwsService;

@RestController
@RequestMapping("rest/admin")
@Slf4j
public class AdminController {

    private final AwsService awsService;

    public AdminController(AwsService awsService) {
        this.awsService = awsService;
    }

    @GetMapping(path = "aws/overview")
    public ResponseEntity<String> getAWSOverview() {
        return ResponseEntity.ok(awsService.getOverview().toString());
    }
}
