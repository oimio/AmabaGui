package ch.amaba.client.view.upload.state;

import java.beans.PropertyChangeListener;

interface State {

	void addPropertyChangeListener(PropertyChangeListener l);

	void addPropertyChangeListener(String propertyName, PropertyChangeListener l);

	void firePropertyChange(String propertyName, Object oldValue, Object newValue);

	void removePropertyChangeListener(PropertyChangeListener l);

	void removePropertyChangeListener(String propertyName, PropertyChangeListener l);
}
