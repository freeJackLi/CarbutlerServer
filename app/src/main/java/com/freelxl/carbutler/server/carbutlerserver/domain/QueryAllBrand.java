package com.freelxl.carbutler.server.carbutlerserver.domain;

import com.freelxl.baselibrary.bean.BaseJson;

import java.util.List;

/**
 * Created by root-pc on 2015/4/10.
 */
public class QueryAllBrand extends BaseJson {

    public List<Data> data;

    public class Data {

        public String brandId;
        public String brandLogo;
        public String brandName;
        public String brandEname;
        public String brandIsHot;
    }
}
