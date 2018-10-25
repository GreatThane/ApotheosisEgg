package org.thane;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import basemod.interfaces.RelicGetSubscriber;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpireInitializer
public class ApotheosisEggMod implements RelicGetSubscriber, EditRelicsSubscriber {
    public static void initialize() {
        new ApotheosisEggMod();
    }

    public ApotheosisEggMod() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receiveRelicGet(AbstractRelic abstractRelic) {
        String id = abstractRelic.relicId.toLowerCase();

        if (id.contains("egg")) {
            long relicCount = AbstractDungeon.player.relics.stream()
                    .filter(r -> r.relicId.toLowerCase().contains("egg"))
                    .count() + 1;
            if (relicCount >= 3) {

                AbstractDungeon.player.relics.stream()
                        .filter(r -> r.relicId.toLowerCase().contains("egg"))
                        .map(r -> r.relicId)
                        .forEach(s -> AbstractDungeon.player.loseRelic(s));
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("Apotheosis Egg").makeCopy());
            }
        }
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new ApotheosisEgg(), RelicType.SHARED);
    }
}
