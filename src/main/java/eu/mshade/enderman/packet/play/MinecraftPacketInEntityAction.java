package eu.mshade.enderman.packet.play;

import eu.mshade.enderframe.metadata.ActionType;
import eu.mshade.enderframe.protocol.MinecraftPacketIn;
import eu.mshade.enderframe.protocol.MinecraftByteBuf;
import eu.mshade.enderframe.protocol.MinecraftSession;

public class MinecraftPacketInEntityAction implements MinecraftPacketIn {

    private int id, actionParameter;
    private ActionType actionType;
    private MinecraftSession minecraftSession;

    @Override
    public void deserialize(MinecraftSession minecraftSession, MinecraftByteBuf minecraftByteBuf) {
        this.minecraftSession = minecraftSession;
        id = minecraftByteBuf.readVarInt();
        actionType = ActionType.getActionTypeByIndex(minecraftByteBuf.readVarInt());
        actionParameter = minecraftByteBuf.readVarInt();
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
    public MinecraftSession getSessionWrapper() {
        return minecraftSession;
    }
}
