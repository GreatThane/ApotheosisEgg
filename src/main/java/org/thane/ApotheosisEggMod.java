package org.thane;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import basemod.interfaces.RelicGetSubscriber;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class ApotheosisEggMod implements RelicGetSubscriber, EditRelicsSubscriber, EditStringsSubscriber {
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
        Logger logger = LogManager.getLogger(ApotheosisEggMod.class.getName());
        logger.info("HELLO THIS IS MY NAME NOTICE ME");
        logger.info(ApotheosisEgg.ID == null);
        logger.info(ApotheosisEgg.IMG == null);
        logger.info(ApotheosisEgg.OUTLINE == null);
        logger.info(new Texture(Gdx.files.internal(ApotheosisEgg.IMG)) == null);
        logger.info(new Texture(Gdx.files.internal(ApotheosisEgg.OUTLINE)) == null);
        ApotheosisEgg apotheosisEgg = new ApotheosisEgg();
        logger.info(apotheosisEgg);
        BaseMod.addRelic(apotheosisEgg, RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        String relicStrings = Gdx.files.internal("localization/relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    }
}
