package com.recr.bot.recrbot.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import com.recr.bot.recrbot.model.entity.enums.Education;
import com.recr.bot.recrbot.model.entity.enums.MilitaryStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@Table(name = "candidat")
@ToString
@EqualsAndHashCode
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class CandidatEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "age")
    private int age;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MilitaryStatus status;
    @Column(name = "relationship_status")
    private String relationshipStatus;
    @Column(name = "education")
    @Enumerated(EnumType.STRING)
    private Education education;
    @Column(name = "profession")
    private String profession;
    @Column(name = "work_exp")
    private String workExp;
    @Column(name = "military_rank")
    private String militaryRank;
    @Column(name = "educational_institution")
    private String educationalInstitution;
    @Column(name = "combat_action_participant" )
    private String combatActionParticipant;
    @Column(name = "helth_status")
    private String helthStatus;
    @Column(name = "recruter")
    private String recruter;
    @Column(name = "atempt")
    private int atempt;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "branch_of_service")
    private String branchOfService;
    @Column(name = "comment")
    private String comment;
    @Column(name = "application_date")
    private LocalDateTime applicationDate;

}
