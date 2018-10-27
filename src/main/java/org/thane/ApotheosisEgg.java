package org.thane;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.GeneticAlgorithm;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.IntStream;

public class ApotheosisEgg extends CustomRelic {

    public static final String ID = "RelicPlusPlus:ApotheosisEgg";
    public static final String IMG = "images/relics/apotheosisEgg.png";

    private Set<AbstractCard> upgradedCards = new HashSet<>();

    public ApotheosisEgg() {
        super(ID,
                new Texture(Gdx.files.internal(IMG)),
                RelicTier.SPECIAL,
                LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.masterDeck.getUpgradableCards().group.forEach(c -> {
            c.upgrade();
            upgradedCards.add(c);
        });
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (c.canUpgrade()) {
            c.upgrade();
            flash();
            upgradedCards.add(c);
        }
    }

    @Override
    public void onUnequip() {
        CardGroup deck = AbstractDungeon.player.masterDeck;
        List<AbstractCard> cards = (List<AbstractCard>) deck.group.clone();
        for (AbstractCard card : cards) {
            if (upgradedCards.contains(card)) {
                deck.removeCard(card);
                upgradedCards.remove(card);

                if (card.cardID.equalsIgnoreCase("Searing Blow")) {
                    SearingBlow blow = (SearingBlow) card;
                    int times = blow.timesUpgraded;
                    SearingBlow newBlow = (SearingBlow) CardLibrary.getCard(card.cardID).makeCopy();
                    IntStream.range(0, times - 2).forEach(i -> newBlow.upgrade());
                    deck.addToTop(newBlow);

                } else if(card.cardID.equalsIgnoreCase("RitualDagger")) {
                    RitualDagger dagger = (RitualDagger) card;
                    RitualDagger newDagger = (RitualDagger) CardLibrary.getCard(card.cardID).makeCopy();
                    newDagger.damage = dagger.damage - dagger.magicNumber;
                    deck.addToTop(newDagger);

                } else if (card.cardID.equalsIgnoreCase("Genetic Algorithm")) {
                    GeneticAlgorithm gene = (GeneticAlgorithm) card;
                    GeneticAlgorithm newGene = (GeneticAlgorithm) CardLibrary.getCard(card.cardID).makeCopy();
                    newGene.block = gene.block - gene.magicNumber;
                    deck.addToTop(gene);

                } else deck.addToTop(CardLibrary.getCard(card.cardID).makeCopy());
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ApotheosisEgg();
    }
}
