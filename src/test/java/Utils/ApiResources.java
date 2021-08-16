package Utils;

public enum ApiResources {
    CreateUserAPI("/public/v1/users"),
    GetUserAPI("/public/v1/users/{userId}");

    private String resource;
    ApiResources(String resource) {
        this.resource = resource;
    }
    public String getResource(){
        return  resource;
    }
}
