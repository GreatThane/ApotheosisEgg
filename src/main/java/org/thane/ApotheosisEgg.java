package org.thane;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ApotheosisEgg extends CustomRelic {

    public static final String ID = "Apotheosis Egg";
    public static final String IMG = "images/relics/apotheosisEgg.png";
    public static final String OUTLINE = "images/relics/outline/apotheosisEgg.png";

//    private Set<UUID> upgradedCards = new HashSet<>();

    public ApotheosisEgg() {
        super(ID,
                new Texture(Gdx.files.internal(IMG)),
                RelicTier.SPECIAL,
                LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.masterDeck.getUpgradableCards().group.forEach(AbstractCard::upgrade);
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (c.canUpgrade()) {
            c.upgrade();
            flash();
//            upgradedCards.add(c.uuid);
        }
    }

    @Override
    public void onUnequip() {
//        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("Molten Egg 2").makeCopy());
//        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("Toxic Egg 2").makeCopy());
//        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("Frozen Egg 2").makeCopy());
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1] + DESCRIPTIONS[2] + DESCRIPTIONS[3];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ApotheosisEgg();
    }
}
