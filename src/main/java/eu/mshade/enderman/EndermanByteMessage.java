package eu.mshade.enderman;

import eu.mshade.enderframe.protocol.ByteMessage;
import io.netty.buffer.ByteBuf;

public class EndermanByteMessage extends ByteMessage {

    public EndermanByteMessage(ByteBuf buf) {
        super(buf);
    }
}
