package deadCellsMod.cn.infinite.stsmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import deadCellsMod.cn.infinite.stsmod.enums.AbstractDeadCellsEnum;

public class SymmetricalSpear extends CustomCard {
    public static final String ID = "deadCells:SymmetricalSpear";
    private  static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    private static final int BASE_DAMAGE = 5;
    private static final int BASE_MULTI = 2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String imgUrl = "img/card/SymmetricalSpear.png";
    private static final int UPGRADE_MULTI = 1;
    //是否可以暴击的标识
    private boolean canCrit=false;



    public SymmetricalSpear(){
        super(ID,NAME,imgUrl,COST,DESCRIPTION,CardType.ATTACK, AbstractDeadCellsEnum.DEAD_CELLS
        ,CardRarity.RARE,CardTarget.ALL_ENEMY);



        this.baseDamage = BASE_DAMAGE;
        this.isMultiDamage = true;
        this.baseMagicNumber = BASE_MULTI;
        this.damageType = DamageInfo.DamageType.NORMAL;


        /*setBackgroundTexture("img/card_bg/512/attack_bg.png",
                   "img/card_bg/1024/attack_bg.png");
        setOrbTexture("img/card_bg/512/energy_orb.png",
                "img/card_bg/512/energy_small.png");*/
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //取消伤害增加
            /*this.baseDamage += UPGRADE_DMA;*/
            this.upgradeMagicNumber(UPGRADE_MULTI);
            this.upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int critDamage = this.baseDamage*2;
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        AbstractPower vigor = AbstractDungeon.player.getPower("Vigor");
        //力量
        if (strength != null){
            critDamage += strength.amount;
        }
        //活力
        if (vigor!=null){
            //暴击伤害
            critDamage += vigor.amount;
        }
        /*int[] amount = new int[this.baseMagicNumber];*/
        for (int i = 1;i<=this.baseMagicNumber;i++){
            //检测第一段伤害击中的敌人数量
            if(i ==1){
                int count = 0;
                //AbstractDungeon是当前房间的抽象类,可以获取当前房间的相关信息
                //一下获取了当前房间怪物的数量
                for (AbstractMonster monster:AbstractDungeon.getMonsters().monsters){
                    //如果有一个怪没死,就会击中那个怪,击中怪的数量加1
                    if (!monster.isDead){
                        count++;
                    }
                    //如果击中的怪物数量大于二,设置当前攻击可以暴击
                    if (count>=2){
                        canCrit=true;
                        //打破循环提高效率
                        break;
                    }
                }
            }
            //视觉特效
            AbstractDungeon.actionManager.addToBottom(new VFXAction(abstractPlayer, new CleaveEffect(), 0.0F));
            //音效
            String key = "III";
            if ( i%3 == 1) {
                key = "I";
            }else if ( i%3 == 2){
                key = "II";
            }
            AbstractDungeon.actionManager.addToBottom(new SFXAction("deadCells:SYMMETRICAL_SPEARS_"+key));

            //使用自定义的音效
            /*原先使用旋风斩音效:AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIRLWIND",canCrit?0.5F:0.0F));*/
            /*amount[i] = canCrit?critDamage:this.baseDamage;弃用的代码,多段和数组长度无关,伤害只取下标为0的元素*/
            //critDamage+power是因为力量的结算是通过代理实现的,伤害翻倍需要将力量造成的伤害也翻倍,所以需要额外加上翻倍的数值
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(abstractPlayer,
                    //对全体敌人造成伤害需要使用baseDamage来计算伤害而不是使用damage
                    canCrit&&i!=1? critDamage:this.baseDamage,
                    this.damageType, AbstractGameAction.AttackEffect.NONE));
        }
        //使用完毕,将卡牌属性回归默认值
        this.canCrit = false;
        /*AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(abstractPlayer,
                amount,this.damageType, AbstractGameAction.AttackEffect.NONE));事实证明数组不是用来打段数的*/
    }

    /*暂时不知是每次调用action时会调用一下方法还是在卡牌使用之前调用
    所以无法用一下代码更改卡牌使用时当前段数造成的伤害
    @Override
    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        //第一段默认不会暴击,第二段需要检测是否可以暴击
        if (this.canCrit){
            if (strength!=null){
                strength.amount *= 2;
            }

            super.applyPowers();

            if (strength!=null){
                strength.amount /= 2;
            }
        }else{
            //不能暴击,力量无需翻倍
            super.applyPowers();
        }
    }*/
    /*@Override
    public AbstractCard makeCopy(){
        AbstractCard card = new SymmetricalSpear();
        if (upgraded) {
            card.upgrade();
        }
        return card;

    }*/
}
