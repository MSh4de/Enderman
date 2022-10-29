package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.metadata.ActionType;
import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;
import eu.mshade.enderframe.protocol.SessionWrapper;

public class PacketInEntityAction implements PacketIn {

    private int id, actionParameter;
    private ActionType actionType;
    private SessionWrapper sessionWrapper;

    @Override
    public void deserialize(SessionWrapper sessionWrapper, ProtocolBuffer protocolBuffer) {
        this.sessionWrapper = sessionWrapper;
        id = protocolBuffer.readVarInt();
        actionType = ActionType.getActionTypeByIndex(protocolBuffer.readVarInt());
        actionParameter = protocolBuffer.readVarInt();
    }

    public int getId() {
        return id;
    }

    public int getActionParameter() {
        return actionParameter;
    }

    public ActionType getActionType() {
        return actionType;
    }

    @Override
    public SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }
}
