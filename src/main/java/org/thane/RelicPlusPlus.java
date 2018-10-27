package org.thane;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import basemod.interfaces.RelicGetSubscriber;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.stream.Collectors;

@SpireInitializer
public class RelicPlusPlus implements EditStringsSubscriber, RelicGetSubscriber, EditRelicsSubscriber {
    public static void initialize() {
        new RelicPlusPlus();
    }

    public static final Logger LOGGER = LogManager.getLogger(RelicPlusPlus.class.getName());

    public RelicPlusPlus() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receiveRelicGet(AbstractRelic abstractRelic) {
        String id = abstractRelic.relicId.toLowerCase();
        Logger logger = LogManager.getLogger(RelicPlusPlus.class.getName());
        if (id.contains("egg")) {
            Set<String> relicIds = AbstractDungeon.player.relics.stream()
                    .map(r -> r.relicId)
                    .filter(s -> s != null && s.toLowerCase().contains("egg"))
                    .collect(Collectors.toSet());
            if (relicIds.contains(ApotheosisEgg.ID)) return;

            if (relicIds.size() >= 3) {

                relicIds.forEach(s -> AbstractDungeon.player.loseRelic(s));
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic(ApotheosisEgg.ID).makeCopy());
                if (Loader.DEBUG) {
                    AbstractDungeon.actionManager.addToBottom(new ApotheosisAction());
                }
            }
        }
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings.class, "RelicPlusPlusAssets/RelicPlusPlusRelics.json");
    }

    @Override
    public void receiveEditRelics() {
        ApotheosisEgg apotheosisEgg = new ApotheosisEgg();
        BaseMod.addRelic(apotheosisEgg, RelicType.SHARED);
    }
}
