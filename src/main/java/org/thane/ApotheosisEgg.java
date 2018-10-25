package org.thane;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ApotheosisEgg extends CustomRelic {

    public static final String ID = "Apotheosis Egg";

    public ApotheosisEgg() {
        super(ID, new Texture("apotheosisEgg.png"), RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.masterDeck.getUpgradableCards().group.forEach(AbstractCard::upgrade);
    }

    @Override
    public void onUnequip() {
//        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("Molten Egg 2").makeCopy());
//        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("Toxic Egg 2").makeCopy());
//        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic("Frozen Egg 2").makeCopy());
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ApotheosisEgg();
    }
}
