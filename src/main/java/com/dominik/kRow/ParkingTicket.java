package com.dominik.kRow;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;

@Indexed
    @Entity
    @Table(name = "parkingticketss")
    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public class ParkingTicket {

        public ParkingTicket() {
        }

        public ParkingTicket(String summonsNumber, String plateId, String state, String plateType, String issueDate, String violationCode, String bodyType, String vehicleMake, String issuingAgency, String streetCode1, String streetCode2, String streetCode3, String vehicleExpirationDate, String violationLocation) {
            this.summonsNumber = summonsNumber;
            this.plateId = plateId;
            this.state = state;
            this.plateType = plateType;
            this.issueDate = issueDate;
            this.violationCode = violationCode;
            this.bodyType = bodyType;
            this.vehicleMake = vehicleMake;
            this.issuingAgency = issuingAgency;
            this.streetCode1 = streetCode1;
            this.streetCode2 = streetCode2;
            this.streetCode3 = streetCode3;
            this.vehicleExpirationDate = vehicleExpirationDate;
            this.violationLocation = violationLocation;
        }

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @GenericField
        private Long id;
        @FullTextField
        @Column(name = "summons_number")
        private String summonsNumber;
        @GenericField
        @Column(name = "plate_id")
        private String plateId;
        @FullTextField
        @Column(name = "registration_state")
        private String state;
        @FullTextField
        @Column(name = "plate_type")
        private String plateType;
        @FullTextField
        @Column(name = "issue_date")
        private String issueDate;
        @FullTextField
        @Column(name = "violation_code")
        private String violationCode;
        @FullTextField
        @Column(name = "vehicle_body_type")
        private String bodyType;
        @FullTextField
        @Column(name = "vehicle_make")
        private String vehicleMake;
        @FullTextField
        @Column(name = "issuing_agency")
        private String issuingAgency;
        @FullTextField
        @Column(name = "street_code1")
        private String streetCode1;
        @FullTextField
        @Column(name = "street_code2")
        private String streetCode2;
        @FullTextField
        @Column(name = "street_code3")
        private String streetCode3;
        @FullTextField
        @Column(name = "vehicle_expiration_date")
        private String vehicleExpirationDate;
        @FullTextField
        @Column(name = "Violation_Location")
        private String violationLocation;
    }
