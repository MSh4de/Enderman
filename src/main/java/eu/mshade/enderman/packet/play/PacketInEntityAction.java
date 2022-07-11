package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.metadata.ActionType;
import eu.mshade.enderframe.protocol.PacketIn;
import eu.mshade.enderframe.protocol.ProtocolBuffer;

public class PacketInEntityAction implements PacketIn {

    private int id, actionParameter;
    private ActionType actionType;

    @Override
    public void deserialize(ProtocolBuffer protocolBuffer) {
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
}
