package com.sicredi.votacao.bootstrap.log;

import java.time.LocalDateTime;

public class LogKibana {

    private static final String appName_ = "api-springwebflux-votacao";

    private String httpReqUrl;
    private String operation;
    private Integer level;
    private String levelName;
    private String timestamp;
    private final String appName;
    private Boolean error;
    private String errorMessage;
    private String bodyRequest;
    private String bodyResponse;

    public LogKibana(String httpReqUrl, String operation, LogLevel level, LocalDateTime timestamp, Boolean error, String errorMessage, String bodyRequest, String bodyResponse) {
        this.httpReqUrl = httpReqUrl;
        this.operation = operation;
        this.level = level.getValue();
        this.levelName = level.toString();
        this.timestamp = timestamp.toString();
        this.appName = appName_;
        this.error = error;
        this.errorMessage = errorMessage;
        this.bodyRequest = bodyRequest;
        this.bodyResponse = bodyResponse;
    }

    public LogKibana(String httpReqUrl, String operation, LogLevel level, LocalDateTime timestamp, Boolean error, String errorMessage) {
        this.httpReqUrl = httpReqUrl;
        this.operation = operation;
        this.level = level.getValue();
        this.levelName = level.toString();
        this.timestamp = timestamp.toString();
        this.appName = appName_;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public String toStringSucefullCase() {
        return "{ " +
                "\"httpReqUrl\":\"" + httpReqUrl + "\"" +
                ", \"operation\":\"" + operation + "\"" +
                ", \"level\":\"" + level + "\"" +
                ", \"levelName\":\"" + levelName + "\"" +
                ", \"timestamp\":\"" + timestamp + "\"" +
                ", \"appName\":\"" + appName + "\"" +
                ", \"error\":\"" + error + "\"" +
                ", \"bodyRequest\":" + bodyRequest +
                ", \"bodyResponse\":" + bodyResponse +
                " }";
    }

    public String toStringErrorCase() {
        return "{ " +
                "\"httpReqUrl\":" + httpReqUrl + "\"" +
                ", \"operation\":\"" + operation + "\"" +
                ", \"level\":\"" + level + "\"" +
                ", \"levelName\":\"" + levelName + "\"" +
                ", \"timestamp\":\"" + timestamp + "\"" +
                ", \"appName\":\"" + appName + "\"" +
                ", \"error\":\"" + error + "\"" +
                ", \"errorMessage\":\"" + errorMessage + "\"" +
                ", \"bodyRequest\":" + bodyRequest +
                ", \"bodyResponse\":" + bodyResponse +
                " }";
    }
}
