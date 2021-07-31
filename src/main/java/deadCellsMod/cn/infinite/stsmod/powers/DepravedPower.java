package deadCellsMod.cn.infinite.stsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import deadCellsMod.cn.infinite.stsmod.character.DepravedCase;
import deadCellsMod.cn.infinite.stsmod.utils.ImgUtils;

public class DepravedPower extends AbstractPower implements CloneablePowerInterface {
    public static final String BASE_ID = "deadCells:DepravedPower";
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(BASE_ID);
    private static final AbstractCreature DP_PLAYER ;

    static{
        DP_PLAYER = new DepravedCase();
    }
    public DepravedPower(AbstractCreature owner,int amount){
        this.ID = BASE_ID;
        this.name = STRINGS.NAME;
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = false;
        ImgUtils.setPowerImg(this);
        this.updateDescription();
        this.type = PowerType.BUFF;
    }

    @Override
    public void updateDescription() {
        this.description = STRINGS.DESCRIPTIONS[0]+amount+STRINGS.DESCRIPTIONS[1];
    }


    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature attackTarget) {
        //比较内存地址,更快捷,如果有bug再改equals
        if (!(DP_PLAYER == info.owner)) {
            int damage = info.base / 4;
            if (damage >= 1) {
                this.flash();
                AbstractCreature ownerSource = this.owner;
                for (int i = 0; i < this.amount; i++) {
                    addToBot(new AbstractGameAction() {
                        @Override
                        public void update() {
                            if (!attackTarget.isDead) {
                                addToBot(new DamageAction(attackTarget, new DamageInfo(DP_PLAYER, damage), AbstractGameAction.AttackEffect.NONE));
                                addToTop(new VFXAction(ownerSource, new LightningEffect(attackTarget.drawX, attackTarget.drawY), 0.0F));
                                addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
                            }
                            this.isDone = true;
                        }
                    });
                }
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DepravedPower(owner,amount);
    }
}
