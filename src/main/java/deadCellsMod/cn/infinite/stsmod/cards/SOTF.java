package deadCellsMod.cn.infinite.stsmod.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Footwork;
import com.megacrit.cardcrawl.cards.red.Inflame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import deadCellsMod.cn.infinite.stsmod.DeadCellsModInitializer;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

import java.util.ArrayList;

public class SOTF extends DeadCellsCard {

    public static final String BASE_ID = "deadCells:SOTF";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public SOTF(){
        super(BASE_ID,STRINGS.NAME,"img/card/SOTF.png",1,STRINGS.DESCRIPTION,CardType.POWER, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);
        this.setBackgroundTexture(DeadCellsModInitializer.GOLD_POWER_CARD, DeadCellsModInitializer.GOLD_POWER_CARD_PORTRAIT);

        this.magicNumber = this.baseMagicNumber = 1;
        this.changeNum = this.baseChangeNum = 1;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            this.rawDescription = STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new InflameEffect((AbstractCreature)abstractPlayer), 0.6F));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,this.magicNumber),this.magicNumber));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,this.magicNumber),this.magicNumber));
        if (this.upgraded){
            ArrayList<AbstractCard> list = new ArrayList();
            list.add(new StrengthPowerII());
            list.add(new DexterityPowerII());

            addToBot(new ChooseOneAction(list));
        }
    }











    public static class StrengthPowerII extends Inflame {

        public StrengthPowerII() {
            super();
            this.magicNumber = this.baseMagicNumber = 1;
            this.color = CardColor.COLORLESS;
            this.rarity = CardRarity.SPECIAL;
            this.cardID = "deadCells:StrengthPowerII";
        }

        @Override
        public void upgrade() { }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            super.use(p, m);
        }

        @Override
        public void onChoseThisOption() {
            super.use(AbstractDungeon.player,AbstractDungeon.getRandomMonster());
        }
    }

















    public static class DexterityPowerII extends Footwork {

        public DexterityPowerII() {
            super();
            this.rarity = CardRarity.SPECIAL;
            this.color = CardColor.COLORLESS;
            this.cardID = "deadCells:DexterityPowerII";
            this.magicNumber = this.baseMagicNumber = 1;
        }

        @Override
        public void upgrade() { }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            super.use(p, m);
        }

        @Override
        public void onChoseThisOption() {
            super.use(AbstractDungeon.player,AbstractDungeon.getRandomMonster());
        }
    }
}
