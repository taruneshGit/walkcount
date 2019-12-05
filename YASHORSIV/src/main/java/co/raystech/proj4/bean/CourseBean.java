package co.raystech.proj4.bean;

public class CourseBean extends BaseBean {

	private String courseName;
	private String courseDescription;
	private String courseDuration;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
