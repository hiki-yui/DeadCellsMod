package deadCellsMod.cn.infinite.stsmod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractCardEnum;
import deadCellsMod.cn.infinite.stsmod.enums.DeadCellsTags;
import deadCellsMod.cn.infinite.stsmod.powers.SmokeBombPower;

public class SmokeBomb extends GrenadeCard {

    public static final String BASE_ID = "deadCells:SmokeBomb";
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(BASE_ID);

    public SmokeBomb(){
        super(BASE_ID,STRINGS.NAME,"img/card/SmokeBomb.png",1,STRINGS.DESCRIPTION,CardType.SKILL, AbstractCardEnum.DEAD_CELLS,CardRarity.UNCOMMON,CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 3;
        this.changeNum = this.baseChangeNum = 1;
        this.exhaust = true;
        this.tags.add(DeadCellsTags.GRENADE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeMagicNumber(3);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        addToBot(new ApplyPowerAction(p,p,new VigorPower(p,this.magicNumber),this.magicNumber));
        addToBot(new ApplyPowerAction(p,p,new SmokeBombPower(p,this.changeNum),this.changeNum));
    }
}
