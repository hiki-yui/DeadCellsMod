package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import deadCellsMod.cn.infinite.stsmod.action.GainBleedingPowerAction;

public class ThrowingKnife extends DeadCellsCard {
    public static final String BASE_ID = "deadCells:ThrowingKnife";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);
    private static final String IMG = "img/card/ThrowingKnife.png";

    public ThrowingKnife(){
        super(BASE_ID,STRINGS.NAME,IMG,0,STRINGS.DESCRIPTION,CardType.ATTACK,CardColor.COLORLESS,CardRarity.SPECIAL,CardTarget.ENEMY);


        this.exhaust = true;
        this.baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 3;
        this.damageType = DamageInfo.DamageType.NORMAL;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeDamage(1);
            this.upgradeMagicNumber(1);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(abstractMonster!=null){
            addToBot(new VFXAction(new ThrowDaggerEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
        }
        addToBot(new SFXAction("deadCells:THROWING_KNIFE"));
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage,this.damageType), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new GainBleedingPowerAction(abstractPlayer,abstractMonster,this.magicNumber));
    }
}
