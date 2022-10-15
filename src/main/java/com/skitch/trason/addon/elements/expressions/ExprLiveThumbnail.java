package com.skitch.trason.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.twitch4j.helix.domain.Stream;
import com.github.twitch4j.helix.domain.StreamList;
import com.skitch.trason.addon.elements.events.bukkit.BridgeEventChat;
import com.skitch.trason.addon.elements.events.bukkit.BridgeEventGoLive;
import org.bukkit.event.Event;

import java.util.Collections;

import static com.skitch.trason.addon.elements.stucture.StructTwitch.client;

public class ExprLiveThumbnail extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprLiveThumbnail.class, String.class, ExpressionType.SIMPLE, "[event-]livethumbnail");
    }

    @Override
    protected String[] get(Event e) {
        if (e  instanceof BridgeEventGoLive) {
            String thumbnail = ((BridgeEventGoLive)e).getEvent().getStream().getThumbnailUrl();
            return new String[]{thumbnail};
        }
        if (e instanceof BridgeEventChat) {
            StreamList list = client.getHelix().getStreams(null, null, null, 1, null, null, null, Collections.singletonList(((BridgeEventChat) e).getEvent().getChannel().getName())).execute();
            Stream str = list.getStreams().get(0);
            String thumbnail = str.getThumbnailUrl(1280,720);
            return new String[]{thumbnail};
        }
        return new String[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "event-livethumbnail";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
