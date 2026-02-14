package com.raeyncreations.silenthill.client.model;

import com.raeyncreations.silenthill.SilentHillMod;

import com.raeyncreations.silenthill.entity.AsphyxiaEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class AsphyxiaModel extends HumanoidModel<AsphyxiaEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SilentHillMod.MOD_ID, "asphyxia"), "main");

    public AsphyxiaModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
    
        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.875F, 0.0F, -0.30543261909900765F, 0.0F, 0.0F));

        PartDefinition Corpo1 = bone.addOrReplaceChild("Corpo1", CubeListBuilder.create()
            .texOffs(42, 42).addBox(-3.5F, 11.69929F, -0.95372F, 7F, 4F, 4F, CubeDeformation.NONE)
            , PartPose.offsetAndRotation(0.0F, 0.8125F, 0.0625F, 0.30543261909900765F, 0.0F, 0.0F));

        PartDefinition LeftLeg2 = Corpo1.addOrReplaceChild("LeftLeg2", CubeListBuilder.create()
            .texOffs(70, 22).addBox(0.2F, 6.69899F, -0.95372F, 3F, 7F, 3F, new CubeDeformation(0.2F))
            , PartPose.offsetAndRotation(0.13125F, 0.856205625F, 0.0653925F, 0.0F, 0.0F, 0.0F));

        PartDefinition LeftLeg3 = LeftLeg2.addOrReplaceChild("LeftLeg3", CubeListBuilder.create()
            .texOffs(0, 85).addBox(1.2F, -0.30101F, -2.95372F, 3F, 2F, 2F, CubeDeformation.NONE)
            .texOffs(66, 69).addBox(1.2F, -0.30101F, -0.95372F, 3F, 7F, 3F, CubeDeformation.NONE)
            , PartPose.offsetAndRotation(0.19375F, 0.418705625F, -0.0596075F, 0.0F, 0.0F, 0.0F));

        PartDefinition RightLeg2 = Corpo1.addOrReplaceChild("RightLeg2", CubeListBuilder.create()
            .texOffs(53, 69).addBox(-3.2F, 6.69899F, -0.95372F, 3F, 7F, 3F, new CubeDeformation(0.2F))
            , PartPose.offsetAndRotation(-0.11875F, 0.856205625F, 0.0653925F, 0.0F, 0.0F, 0.0F));

        PartDefinition RightLeg3 = RightLeg2.addOrReplaceChild("RightLeg3", CubeListBuilder.create()
            .texOffs(0, 66).addBox(-4.2F, -0.30101F, -0.95372F, 3F, 7F, 3F, CubeDeformation.NONE)
            .texOffs(84, 13).addBox(-4.2F, -0.30101F, -2.95372F, 3F, 2F, 2F, CubeDeformation.NONE)
            , PartPose.offsetAndRotation(-0.18125F, 0.418705625F, -0.0596075F, 0.0F, 0.0F, 0.0F));

        PartDefinition body = Corpo1.addOrReplaceChild("body", CubeListBuilder.create()
            .texOffs(0, 42).addBox(-3.5F, 18.69929F, -1.95372F, 7F, 5F, 4F, new CubeDeformation(0.001F))
            .texOffs(67, 39).addBox(-2.5F, 15.69929F, -1.5F, 5F, 3F, 3F, new CubeDeformation(0.2F))
            , PartPose.offsetAndRotation(0.0F, 1.106205625F, 0.0653925F, -0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition RightArm = body.addOrReplaceChild("RightArm", CubeListBuilder.create()
            .texOffs(60, 11).addBox(-5.75882F, 13.66522F, -1.95372F, 3F, 10F, 3F, new CubeDeformation(-0.3F))
            .texOffs(13, 81).addBox(-5.75882F, 12.16522F, -1.95372F, 3F, 2F, 3F, new CubeDeformation(-0.4F))
            .texOffs(19, 0).addBox(-3.61974F, 13.28719F, -1.95372F, 1F, 1F, 1F, new CubeDeformation(-0.2F))
            , PartPose.offsetAndRotation(-0.25F, 1.418705625F, -0.0596075F, 0.4363323129985824F, 0.0F, 0.3490658503988659F));

        PartDefinition LeftArm = body.addOrReplaceChild("LeftArm", CubeListBuilder.create()
            .texOffs(57, 28).addBox(2.75882F, 13.66522F, -1.95372F, 3F, 10F, 3F, new CubeDeformation(-0.3F))
            .texOffs(62, 80).addBox(2.75882F, 12.16522F, -1.95372F, 3F, 2F, 3F, new CubeDeformation(-0.4F))
            .texOffs(0, 15).addBox(2.61974F, 13.28719F, -1.95372F, 1F, 1F, 1F, new CubeDeformation(-0.2F))
            , PartPose.offsetAndRotation(0.25F, 1.418705625F, -0.0596075F, 0.4363323129985824F, 0.0F, -0.3490658503988659F));

        PartDefinition Booba = body.addOrReplaceChild("Booba", CubeListBuilder.create()
            .texOffs(79, 65).addBox(-3.5F, 20.19456F, -4F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            .texOffs(76, 77).addBox(0.5F, 20.19456F, -4F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            , PartPose.offsetAndRotation(0.0F, 1.293705625F, -0.0596075F, 0.0F, 0.0F, 0.0F));

        PartDefinition Booba2 = Booba.addOrReplaceChild("Booba2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.293705625F, 0.1278925F, 0.04363323129985824F, 0.0F, 0.0F));

        PartDefinition Corpo0 = Corpo1.addOrReplaceChild("Corpo0", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.4375F, -0.125F, 0.4799655442984406F, 0.0F, 0.0F));

        PartDefinition body2 = Corpo0.addOrReplaceChild("body2", CubeListBuilder.create()
            .texOffs(23, 36).addBox(-3.5F, 25.71753F, -3.36756F, 7F, 5F, 4F, new CubeDeformation(0.001F))
            .texOffs(83, 54).addBox(-1.5F, 30.71753F, -2.36756F, 3F, 2F, 2F, CubeDeformation.NONE)
            .texOffs(39, 65).addBox(-2.5F, 22.71753F, -2.91384F, 5F, 3F, 3F, new CubeDeformation(0.2F))
            , PartPose.offsetAndRotation(0.0F, 1.42738375F, -0.065725F, -0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition head2 = body2.addOrReplaceChild("head2", CubeListBuilder.create()
            .texOffs(0, 0).addBox(-3F, 30.21753F, -4.36756F, 6F, 8F, 6F, new CubeDeformation(-0.4F))
            , PartPose.offsetAndRotation(0.0F, 1.982345625F, -0.0854725F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftear2 = head2.addOrReplaceChild("leftear2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.3125F, 2.232345625F, -0.0854725F, 0.0F, 0.0F, -0.5235987755982988F));

        PartDefinition rightear2 = head2.addOrReplaceChild("rightear2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3125F, 2.232345625F, -0.0854725F, 0.0F, 0.0F, 0.5235987755982988F));

        PartDefinition hat2 = head2.addOrReplaceChild("hat2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.857345625F, -0.0854725F, 0.0F, 0.0F, 0.0F));

        PartDefinition RightArm2 = body2.addOrReplaceChild("RightArm2", CubeListBuilder.create()
            .texOffs(13, 57).addBox(-5.75882F, 20.68346F, -3.36756F, 3F, 10F, 3F, new CubeDeformation(-0.3F))
            .texOffs(49, 80).addBox(-5.75882F, 19.18346F, -3.36756F, 3F, 2F, 3F, new CubeDeformation(-0.4F))
            .texOffs(0, 3).addBox(-3.61974F, 20.30543F, -3.36756F, 1F, 1F, 1F, new CubeDeformation(-0.2F))
            , PartPose.offsetAndRotation(-0.25F, 1.857345625F, -0.1479725F, 0.0F, 0.0F, 0.3490658503988659F));

        PartDefinition LeftArm2 = body2.addOrReplaceChild("LeftArm2", CubeListBuilder.create()
            .texOffs(0, 52).addBox(2.75882F, 20.68346F, -3.36756F, 3F, 10F, 3F, new CubeDeformation(-0.3F))
            .texOffs(36, 80).addBox(2.75882F, 19.18346F, -3.36756F, 3F, 2F, 3F, new CubeDeformation(-0.4F))
            .texOffs(0, 0).addBox(2.61974F, 20.30543F, -3.36756F, 1F, 1F, 1F, new CubeDeformation(-0.2F))
            , PartPose.offsetAndRotation(0.25F, 1.857345625F, -0.1479725F, 0.0F, 0.0F, -0.3490658503988659F));

        PartDefinition Booba3 = body2.addOrReplaceChild("Booba3", CubeListBuilder.create()
            .texOffs(0, 77).addBox(-3.5F, 27.2128F, -5.41384F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            .texOffs(23, 76).addBox(0.5F, 27.2128F, -5.41384F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            , PartPose.offsetAndRotation(0.0F, 1.732345625F, -0.1479725F, 0.0F, 0.0F, 0.0F));

        PartDefinition BracoDaCabeca = body2.addOrReplaceChild("BracoDaCabeca", CubeListBuilder.create()
            .texOffs(26, 84).addBox(-4.5F, 25.66938F, -5.58287F, 2F, 5F, 2.07107F, new CubeDeformation(-0.02F))
            .texOffs(83, 21).addBox(2.5F, 25.66938F, -5.58287F, 2F, 5F, 2.07107F, new CubeDeformation(-0.02F))
            .texOffs(81, 36).addBox(-4.5F, 25.66938F, -3.58287F, 2F, 2F, 3.07107F, new CubeDeformation(-0.02F))
            .texOffs(48, 14).addBox(2.5F, 25.66938F, -3.58287F, 2F, 2F, 3.07107F, new CubeDeformation(-0.02F))
            , PartPose.offsetAndRotation(0.0F, 1.794845625F, -0.0854725F, 2.8361600344907854F, 0.0F, 0.0F));

        PartDefinition Corpo2 = bone.addOrReplaceChild("Corpo2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.8125F, 0.1875F, -0.39269908169872414F, 0.0F, 0.0F));

        PartDefinition body3 = Corpo2.addOrReplaceChild("body3", CubeListBuilder.create()
            .texOffs(0, 32).addBox(-3.5F, 5.14566F, 2.58268F, 7F, 5F, 4F, new CubeDeformation(0.001F))
            .texOffs(0, 27).addBox(-1.5F, 10.14566F, 3.58268F, 3F, 2F, 2F, CubeDeformation.NONE)
            .texOffs(59, 62).addBox(-2.5F, 2.14566F, 3.0364F, 5F, 3F, 3F, new CubeDeformation(0.2F))
            , PartPose.offsetAndRotation(0.0F, 0.25910375F, 0.3489175F, -0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition head3 = body3.addOrReplaceChild("head3", CubeListBuilder.create()
            .texOffs(20, 22).addBox(-3F, 8.64566F, 1.58268F, 6F, 8F, 5F, new CubeDeformation(-0.4F))
            , PartPose.offsetAndRotation(0.0F, 0.69660375F, 0.2864175F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftear3 = head3.addOrReplaceChild("leftear3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.3125F, 0.94660375F, 0.2864175F, 0.0F, 0.0F, -0.5235987755982988F));

        PartDefinition rightear3 = head3.addOrReplaceChild("rightear3", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3125F, 0.94660375F, 0.2864175F, 0.0F, 0.0F, 0.5235987755982988F));

        PartDefinition hat3 = head3.addOrReplaceChild("hat3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.57160375F, 0.2864175F, 0.0F, 0.0F, 0.0F));

        PartDefinition RightArm3 = body3.addOrReplaceChild("RightArm3", CubeListBuilder.create()
            .texOffs(49, 51).addBox(-5.75882F, 0.11159F, 2.58268F, 3F, 10F, 3F, new CubeDeformation(-0.3F))
            .texOffs(80, 30).addBox(-5.75882F, -1.38841F, 2.58268F, 3F, 2F, 3F, new CubeDeformation(-0.4F))
            , PartPose.offsetAndRotation(-0.25F, 0.57160375F, 0.2239175F, 1.0035643198967394F, 0.0F, 0.3490658503988659F));

        PartDefinition LeftArm3 = body3.addOrReplaceChild("LeftArm3", CubeListBuilder.create()
            .texOffs(36, 51).addBox(2.75882F, 0.11159F, 2.58268F, 3F, 10F, 3F, new CubeDeformation(-0.3F))
            .texOffs(78, 0).addBox(2.75882F, -1.38841F, 2.58268F, 3F, 2F, 3F, new CubeDeformation(-0.4F))
            , PartPose.offsetAndRotation(0.25F, 0.57160375F, 0.2239175F, 1.0035643198967394F, 0.0F, -0.3490658503988659F));

        PartDefinition Booba5 = body3.addOrReplaceChild("Booba5", CubeListBuilder.create()
            .texOffs(75, 46).addBox(-3.5F, 6.64093F, -0.4636F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            .texOffs(73, 57).addBox(0.5F, 6.64093F, -0.4636F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            , PartPose.offsetAndRotation(0.0F, 0.44660375F, 0.1614175F, 0.2181661564992912F, 0.0F, 0.0F));

        PartDefinition Booba6 = Booba5.addOrReplaceChild("Booba6", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.44660375F, 0.3489175F, 0.04363323129985824F, 0.0F, 0.0F));

        PartDefinition BracoDaCabeca2 = Corpo2.addOrReplaceChild("BracoDaCabeca2", CubeListBuilder.create()
            .texOffs(83, 21).addBox(1.95324F, 11.67818F, -0.90563F, 2F, 5F, 2.07107F, new CubeDeformation(-0.02F))
            .texOffs(83, 21).addBox(-3.95324F, 11.67818F, -0.90563F, 2F, 5F, 2.07107F, new CubeDeformation(-0.02F))
            .texOffs(48, 14).addBox(2.37657F, 12.12666F, -2.29442F, 2F, 2F, 3.07107F, new CubeDeformation(-0.02F))
            .texOffs(48, 14).addBox(-4.37657F, 12.12666F, -2.29442F, 2F, 2F, 3.07107F, new CubeDeformation(-0.02F))
            , PartPose.offsetAndRotation(0.0F, 0.92738375F, 0.184275F, 1.3089969389957472F, 0.0F, 0.0F));

        PartDefinition Corpo3 = bone.addOrReplaceChild("Corpo3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.625F, 0.25F, -0.8290313946973066F, 0.0F, 0.0F));

        PartDefinition body4 = Corpo3.addOrReplaceChild("body4", CubeListBuilder.create()
            .texOffs(25, 11).addBox(-3.5F, -2.76853F, 2.66034F, 7F, 5F, 4F, new CubeDeformation(0.001F))
            .texOffs(61, 0).addBox(-2.5F, -5.76853F, 3.11406F, 5F, 3F, 3F, new CubeDeformation(0.2F))
            , PartPose.offsetAndRotation(0.0F, -0.235533125F, 0.35377125F, -0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition head4 = body4.addOrReplaceChild("head4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.201966875F, 0.29127125F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftear4 = head4.addOrReplaceChild("leftear4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.3125F, 0.451966875F, 0.29127125F, 0.0F, 0.0F, -0.5235987755982988F));

        PartDefinition rightear4 = head4.addOrReplaceChild("rightear4", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3125F, 0.451966875F, 0.29127125F, 0.0F, 0.0F, 0.5235987755982988F));

        PartDefinition hat4 = head4.addOrReplaceChild("hat4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.076966875F, 0.29127125F, 0.0F, 0.0F, 0.0F));

        PartDefinition RightArm4 = body4.addOrReplaceChild("RightArm4", CubeListBuilder.create()
            .texOffs(48, 0).addBox(-5.75882F, -7.8026F, 2.66034F, 3F, 10F, 3F, new CubeDeformation(-0.3F))
            .texOffs(73, 15).addBox(-5.75882F, -9.3026F, 2.66034F, 3F, 2F, 3F, new CubeDeformation(-0.4F))
            , PartPose.offsetAndRotation(-0.25F, 0.076966875F, 0.22877125F, 0.894223107450724F, -0.5391702541187419F, 0.7401171667507822F));

        PartDefinition LeftArm4 = body4.addOrReplaceChild("LeftArm4", CubeListBuilder.create()
            .texOffs(23, 46).addBox(2.75882F, -7.8026F, 2.66034F, 3F, 10F, 3F, new CubeDeformation(-0.3F))
            .texOffs(70, 33).addBox(2.75882F, -9.3026F, 2.66034F, 3F, 2F, 3F, new CubeDeformation(-0.4F))
            , PartPose.offsetAndRotation(0.25F, 0.076966875F, 0.22877125F, 0.894223107450724F, 0.5391702541187419F, -0.7401171667507822F));

        PartDefinition Booba7 = body4.addOrReplaceChild("Booba7", CubeListBuilder.create()
            .texOffs(73, 7).addBox(-3.5F, -1.27326F, -0.38594F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            .texOffs(36, 72).addBox(0.5F, -1.27326F, -0.38594F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            , PartPose.offsetAndRotation(0.0F, -0.048033125F, 0.16627125F, 0.2617993877991494F, 0.0F, 0.0F));

        PartDefinition Booba8 = Booba7.addOrReplaceChild("Booba8", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.048033125F, 0.35377125F, 0.04363323129985824F, 0.0F, 0.0F));

        PartDefinition Corpo4 = Corpo3.addOrReplaceChild("Corpo4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.3125F, 0.25F, 0.2181661564992912F, 0.0F, 0.0F));

        PartDefinition body5 = Corpo4.addOrReplaceChild("body5", CubeListBuilder.create()
            .texOffs(25, 0).addBox(-3.5F, -9.54491F, 5.03498F, 7F, 6F, 4F, new CubeDeformation(0.001F))
            .texOffs(43, 21).addBox(-2.5F, -14.54491F, 5.4887F, 5F, 6F, 3F, new CubeDeformation(0.2F))
            .texOffs(0, 15).addBox(-3.5F, -19.54491F, 3.4887F, 7F, 6F, 5F, new CubeDeformation(0.2F))
            , PartPose.offsetAndRotation(0.0F, -0.659056875F, 0.50218625F, -0.3490658503988659F, 0.0F, 0.0F));

        PartDefinition head5 = body5.addOrReplaceChild("head5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.221556875F, 0.43968625F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftear5 = head5.addOrReplaceChild("leftear5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.3125F, 0.028443125F, 0.43968625F, 0.0F, 0.0F, -0.5235987755982988F));

        PartDefinition rightear5 = head5.addOrReplaceChild("rightear5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.3125F, 0.028443125F, 0.43968625F, 0.0F, 0.0F, 0.5235987755982988F));

        PartDefinition hat5 = head5.addOrReplaceChild("hat5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.346556875F, 0.43968625F, 0.0F, 0.0F, 0.0F));

        PartDefinition RightArm5 = body5.addOrReplaceChild("RightArm5", CubeListBuilder.create()
            .texOffs(62, 48).addBox(-5.75882F, -12.57898F, 5.03498F, 3F, 8F, 3F, new CubeDeformation(-0.3F))
            , PartPose.offsetAndRotation(-0.25F, -0.346556875F, 0.37718625F, 0.2945072070473736F, -0.18357268352721234F, 0.4676236230258879F));

        PartDefinition LeftArm5 = body5.addOrReplaceChild("LeftArm5", CubeListBuilder.create()
            .texOffs(26, 62).addBox(2.75882F, -12.57898F, 5.03498F, 3F, 8F, 3F, new CubeDeformation(-0.3F))
            , PartPose.offsetAndRotation(0.25F, -0.346556875F, 0.37718625F, 0.2945072070473736F, 0.18357268352721234F, -0.4676236230258879F));

        PartDefinition Booba9 = body5.addOrReplaceChild("Booba9", CubeListBuilder.create()
            .texOffs(13, 71).addBox(-3.5F, -8.04964F, 2.9887F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            .texOffs(43, 31).addBox(0.5F, -8.04964F, 2.9887F, 3F, 4F, 3.07107F, new CubeDeformation(-0.02F))
            , PartPose.offsetAndRotation(0.0F, -0.471556875F, 0.37718625F, 0.0F, 0.0F, 0.0F));

        PartDefinition Booba10 = Booba9.addOrReplaceChild("Booba10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.471556875F, 0.56468625F, 0.04363323129985824F, 0.0F, 0.0F));
    
        return LayerDefinition.create(meshdefinition, 96, 96);
    }

    @Override
    public void setupAnim(AsphyxiaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, netHeadPitch);
    }
}