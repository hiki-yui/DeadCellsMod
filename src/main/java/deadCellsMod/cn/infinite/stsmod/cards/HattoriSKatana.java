package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class HattoriSKatana extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:HattoriSKatana";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private boolean canMove = true;

    public HattoriSKatana(){
        super(BASE_ID,STRINGS.NAME,"img/card/HattoriSKatana.png",1,STRINGS.DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS,CardRarity.RARE,CardTarget.ENEMY);

        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 1;
        this.changeNum = this.baseChangeNum = 1;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            this.upgradeDamage(3);
            this.upgradeName();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.isMagicNumberModified = this.baseMagicNumber != this.magicNumber;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.canMove = false;
        if (this.magicNumber>=3){
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,this.changeNum),this.changeNum));
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new DexterityPower(abstractPlayer,this.changeNum),this.changeNum));
        }
        for (int i = 0;i<this.magicNumber;i++) {
            AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
            int rnd = AbstractDungeon.cardRng.random(4);
            switch (rnd){
                case 0:
                    effect = AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
                    break;
                case 1:
                    effect = AbstractGameAction.AttackEffect.LIGHTNING;
                    break;
                case 2:
                    effect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
                    break;
                case 3:
                    effect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
                    break;
                case 4:
                    effect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
                    break;
            }
            addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage),effect,true));
        }
        this.upgradeMagicNumber(1);
    }

    @Override
    public void onMoveToDiscard() {
        if (!canMove){
            if (AbstractDungeon.player.hand.size()<10) {
                AbstractDungeon.player.discardPile.moveToHand(this);
            }
            canMove = true;
        }
    }
}
