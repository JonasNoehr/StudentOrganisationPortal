package de.hsba.bi.StuOrgPortal.course;

public class CourseEntry {

    private Long id;

    private Long lecturerId;

    private Long studentId;

    private String title;

    private String description;

    private String status;

    private Integer roomNr;

    private Integer maxParticipants;

    private Double averageGrade;

    private Double averageRating;

    private String examType;

    private Double duration;

    private Integer weeklyHours;

    public CourseEntry(String title, String description, String status, Integer roomNr, Integer maxParticipants, Double averageGrade, Double averageRating, String examType, Double duration, Integer weeklyHours) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.roomNr = roomNr;
        this.maxParticipants = maxParticipants;
        this.averageGrade = averageGrade;
        this.averageRating = averageGrade;
        this.examType = examType;
        this.duration = duration;
        this.weeklyHours = weeklyHours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(Integer roomNr) {
        this.roomNr = roomNr;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(Integer weeklyHours) {
        this.weeklyHours = weeklyHours;
    }
}
