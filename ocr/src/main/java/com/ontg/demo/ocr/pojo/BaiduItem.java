package com.ontg.demo.ocr.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class BaiduItem {

    private String words;
    private ItemLocation location;
    private List<ItemChars> chars;

    @Data
    public class ItemChars {
        @JSONField(name = "char")
        private String char_name;
        private ItemLocation location;
    }

}
