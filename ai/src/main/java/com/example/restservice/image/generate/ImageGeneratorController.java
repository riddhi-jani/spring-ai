package com.example.restservice.image.generate;

import org.springframework.ai.image.*;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring/ai")
public class ImageGeneratorController {

    private final ImageClient imageClient;

    public ImageGeneratorController(ImageClient imageClient) {
        this.imageClient = imageClient;
    }

    @PostMapping("/imagegen")
    public String generateImage(@RequestBody OpenAiImageApi.OpenAiImageRequest imageRequest){
        ImageOptions imageOptions = ImageOptionsBuilder.builder().withModel("dall-e-2").build();
        ImagePrompt imagePrompt = new ImagePrompt(imageRequest.prompt(), imageOptions);
        ImageResponse imageResponse = imageClient.call(imagePrompt);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        return "redirect:"+imageUrl;
    }
}
