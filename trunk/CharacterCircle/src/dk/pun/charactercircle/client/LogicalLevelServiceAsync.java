package dk.pun.charactercircle.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LogicalLevelServiceAsync {

	void greetServer(String input, AsyncCallback<String> callback);

}
