package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.metadata.ActionType;
import eu.mshade.enderframe.protocol.ByteMessage;
import eu.mshade.enderframe.protocol.PacketIn;

public class PacketInEntityAction extends PacketIn {

    private int id, actionParameter;
    private ActionType actionType;


    @Override
    public void deserialize(ByteMessage byteMessage) {
        id = byteMessage.readVarInt();
        actionType = ActionType.getActionTypeByIndex(byteMessage.readVarInt());
        actionParameter = byteMessage.readVarInt();
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
