package io.student.dto;




public class RoleDTO {

    private String id;

    private String role;

    public RoleDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RoleDTO(String id, String role) {
        this.id = id;
        this.role = role;
    }


    @Override
    public String toString() {
        return "RoleDTO{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
