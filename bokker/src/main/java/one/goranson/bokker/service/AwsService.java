package one.goranson.bokker.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;

@Service
public class AwsService {
    private final AmazonS3 amazonS3;
    private final AmazonSQS amazonSQS;

    public AwsService(AmazonS3 amazonS3, AmazonSQS amazonSQS) {
        this.amazonS3 = amazonS3;
        this.amazonSQS = amazonSQS;
    }

    public JSONObject getOverview() {
        var jsonObject = new JSONObject();
        populateBuckets(jsonObject);
        populateQueues(jsonObject);
        return jsonObject;
    }

    private void populateBuckets(final JSONObject jsonObject) {
        var buckets = amazonS3.listBuckets();
        if (buckets.size() > 0) {
            jsonObject.put("buckets", buckets);
        }
    }

    private void populateQueues(final JSONObject jsonObject) {
        var queues = amazonSQS.listQueues();
        if (queues.getQueueUrls().size() > 0) {
            jsonObject.put("queues", queues.getQueueUrls());
        }
    }
}
