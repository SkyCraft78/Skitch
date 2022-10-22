package com.trason.skitch.elements.events.bukkit;

import com.github.twitch4j.chat.events.channel.FollowEvent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BridgeEventFollow extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final FollowEvent event;

    public BridgeEventFollow(FollowEvent event) {
        this.event = event;
    }

    public FollowEvent getEvent() {
        return event;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() { return handlers; }

}
