package com.skitch.trason.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.twitch4j.helix.domain.Stream;
import com.github.twitch4j.helix.domain.StreamList;
import com.skitch.trason.addon.elements.events.bukkit.BridgeEventChat;
import org.bukkit.event.Event;

import java.util.Collections;

import static com.skitch.trason.addon.elements.stucture.StructTwitch.client;

public class ExprLiveMessage extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprLiveMessage.class, String.class, ExpressionType.SIMPLE, "[event-]livemessage");
    }

    @Override
    protected
    String[] get(Event e) {
        if (e instanceof BridgeEventChat) {
            String msg = ((BridgeEventChat) e).getEvent().getMessage();
            return new String[]{msg};
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
        return "event-livemessage";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (!ParserInstance.get().isCurrentEvent(BridgeEventChat.class)) {
            Skript.error("You cannot use event-livemessage outside an LiveMessage event!");
            return false;
        }
        return true;
    }
}
