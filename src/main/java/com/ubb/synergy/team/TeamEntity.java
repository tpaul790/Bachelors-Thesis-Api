package com.ubb.synergy.team;

import com.ubb.synergy.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "teams")
public class TeamEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "team",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<MemberEntity> members;

    public Integer[] getIcons() {
        return members.stream()
                .map(m -> m.getUser().getIconNumber())
                .toArray(Integer[]::new);
    }
}
