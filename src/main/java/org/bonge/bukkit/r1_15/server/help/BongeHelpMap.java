package org.bonge.bukkit.r1_15.server.help;

import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicFactory;

import java.util.*;

public class BongeHelpMap implements HelpMap {

    private List<String> plugins = new ArrayList<>();
    private Set<HelpTopic> topics = new HashSet<>();

    @Override
    public HelpTopic getHelpTopic(String topicName) {
        return this.topics.stream().filter(h -> h.getName().equals(topicName)).findAny().orElse(null);
    }

    @Override
    public Collection<HelpTopic> getHelpTopics() {
        return this.topics;
    }

    @Override
    public void addTopic(HelpTopic topic) {
        this.topics.add(topic);
    }

    @Override
    public void clear() {
        this.topics.clear();
    }

    @Override
    public void registerHelpTopicFactory(Class<?> commandClass, HelpTopicFactory<?> factory) {

    }

    @Override
    public List<String> getIgnoredPlugins() {
        return this.plugins;
    }
}
