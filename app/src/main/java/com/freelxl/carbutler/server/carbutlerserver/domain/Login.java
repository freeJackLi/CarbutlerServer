package com.freelxl.carbutler.server.carbutlerserver.domain;

import com.freelxl.baselibrary.bean.BaseJson;

/**
 * Created by root-pc on 2015/4/10.
 */
public class Login extends BaseJson {

    public Data data;

    public class Data {

        public String token;

        public Smember smember;

        public class Smember {

            public String smemberId;
            public String smemberStatus;
            public String smemberType;
        }
    }
}
