package life.majiang.community.community.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {
    public String client_id;
    public String client_secret;
    public String code;
    public String redirect_uri;
    public String state;

}
