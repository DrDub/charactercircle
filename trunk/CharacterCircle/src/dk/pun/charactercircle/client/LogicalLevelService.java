package dk.pun.charactercircle.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("logicalLevel")
public interface LogicalLevelService extends RemoteService {

	String getEnvironments();

}
