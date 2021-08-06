package deadCellsMod.cn.infinite.stsmod.monster;

import basemod.abstracts.CustomMonster;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Zombie extends CustomMonster {

    public static final String BASE_ID = "deadCells:Zombie";
    public static final MonsterStrings STRINGS = CardCrawlGame.languagePack.getMonsterStrings(BASE_ID);
    private static int BASE_HEAVY_DAMAGE = 14;
    private static int BASE_DEBUFF_DAMAGE = 6;
    private static int BASE_BLOCK = 8;
    private static int BASE_PLATED = 3;
    private static boolean ascensionLevel17 = false;
    private boolean firstMove = true;
    private boolean secondMove = true;
    private boolean thirdMove = true;



    public Zombie(float x, float y){
        super(STRINGS.NAME,BASE_ID,61,-8.0F, 10.0F, 230.0F, 240.0F,"img/monster/Zombie.png",x,y,false);

        if (AbstractDungeon.ascensionLevel >= 7){
            setHp(54,61);
        }else{
            setHp(50,57);
        }
        if (AbstractDungeon.ascensionLevel  >= 2 ){
            BASE_HEAVY_DAMAGE += 1;
            BASE_DEBUFF_DAMAGE += 1;
        }
        this.damage.add(new DamageInfo(this,BASE_HEAVY_DAMAGE, DamageInfo.DamageType.NORMAL));
        this.damage.add(new DamageInfo(this,BASE_DEBUFF_DAMAGE, DamageInfo.DamageType.NORMAL));
        if (AbstractDungeon.ascensionLevel >= 17){
            BASE_BLOCK += 1;
            BASE_PLATED += 1;
            ascensionLevel17 = true;
        }
        this.type = EnemyType.NORMAL;
    }

    @Override
    public void takeTurn() {
        switch (this.nextMove){
            case 1:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
                addToBot(new DamageAction(AbstractDungeon.player,this.damage.get(0), AbstractGameAction.AttackEffect.SMASH));
                break;
                case 2:
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AnimateSlowAttackAction((AbstractCreature)this));
                    addToBot(new DamageAction(AbstractDungeon.player,this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                    addToBot(new ApplyPowerAction(AbstractDungeon.player,this,new WeakPower(AbstractDungeon.player,1,true),1));
                    break;
                    case 3:
                        addToBot(new GainBlockAction(this,BASE_BLOCK));
                        addToBot(new ApplyPowerAction(this,this,new PlatedArmorPower(this,BASE_PLATED),BASE_PLATED));
                        break;
        }
        addToBot(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int i) {
        if (this.firstMove && ascensionLevel17) {
            this.setMove((byte) 2, Intent.ATTACK_DEBUFF, BASE_DEBUFF_DAMAGE);
            firstMove = false;
            return;
        }
        if ((this.lastMove((byte)2)) && ascensionLevel17  && secondMove){
            this.setMove((byte)3,Intent.DEFEND_BUFF);
            secondMove = false;
            return;
        }
        if (this.lastMove((byte)3) && ascensionLevel17 && thirdMove){
            this.setMove((byte)1,Intent.ATTACK,BASE_HEAVY_DAMAGE);
            this.thirdMove = false;
            return;
        }
        if (i>40){
            if (this.lastMove((byte)1) && this.lastTwoMoves((byte)1)){
                this.setMove((byte) 2, Intent.ATTACK_DEBUFF, BASE_DEBUFF_DAMAGE);
                return;
            }
            this.setMove((byte)1,Intent.ATTACK,BASE_HEAVY_DAMAGE);
        }else{
            int amount = 0;
            AbstractPower power = this.getPower("Plated Armor");
            if (power!=null){
                amount = power.amount;
            }
            if (this.lastMove((byte)2) && amount<=8){
                this.setMove((byte)3,Intent.DEFEND_BUFF);
                return;
            }else if(this.lastMove((byte)2)){
                this.setMove((byte)1,Intent.ATTACK,BASE_HEAVY_DAMAGE);
                return;
            }
            this.setMove((byte) 2, Intent.ATTACK_DEBUFF, BASE_DEBUFF_DAMAGE);
        }/*else{
            this.setMove((byte)3,Intent.DEFEND_BUFF);
        }*/
    }


}
