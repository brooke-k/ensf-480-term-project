package ensf480.group14.dbcontrol;

import ensf480.group14.forms.PreferenceForm;

public interface DatabaseObserver {
	public void updatePreferences(PreferenceForm preferences);

	public void getNotifiedOfDBChange();
}
