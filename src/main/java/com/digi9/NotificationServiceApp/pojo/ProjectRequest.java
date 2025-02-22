package com.digi9.NotificationServiceApp.pojo;


public class ProjectRequest {
    private String projectId;

    public ProjectRequest() {
    }

    public ProjectRequest(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

	@Override
	public String toString() {
		return "ProjectRequest [projectId=" + projectId + "]";
	}
}

