package ensf480.group14.dbcontrol;

public interface DatabaseSubject {
	void addObserver(DatabaseObserver dbo);

	void removeObserver(DatabaseObserver dbo);

	void notifiyAllObservers();
}
