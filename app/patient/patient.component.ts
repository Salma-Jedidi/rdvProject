import { Component, OnInit } from '@angular/core';
import { PatientService } from '../services/patient.service';
import { Patient } from '../models/Patient';
import { AdminService } from '../services/admin.service';
import { RDV } from '../models/RDV';
import { Delegation } from '../models/Delegation';
import { Medecin } from '../models/Medecin';
import { Specialite } from '../models/Specialite';
import { ServiceMed } from '../models/ServiceMed';
import { Document } from '../models/Document';
declare var $: any; 

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})

export class PatientComponent  implements OnInit {
  selectedPaiement: string = '';
  cinPatient: any;
 
  patient:Patient={
      idPatient: 0,
      nomPatient: '',
      dateNaissance: undefined,
      cin: 0,
      telephone: 0,
      email: '',
      nomDelegation: '',
      role: 'PATIENT',
      typeCaisse: 'CNRPS',
      modePaiement: 'Especes',
      dateCreation: undefined,
      
    };
    rdv: RDV = {
      idRDV: 0,
      dateRDV: undefined,
      heureRdv: undefined,
      remarques: '',
      etatRDV:'',
      nomDuPatient:'',
      nomDuMedecin:'',
      nomDelegation:''
    };
    medecin: Medecin = {
      idMedecin: 0,
      nomMedecin: '',
      cinMedecin: 0,
      tel: '',
      prixConsultation: 0,
      etatMedecin: 0,
      libelleSpecialite: '',
      libelleService: '',
      nomDeletablissement: '',
      delegationMedecin: ''
    };
     rdvs: RDV[] = [];
     rdvPasses: RDV[] = [];
     rdvAVenir: RDV[] = [];
     selectedStartDate: Date | null = null;
     selectedEndDate: Date | null = null;
    medecins: Medecin[] = [];
    delegations: Delegation[] = [];
    specialites: Specialite[] = [];
    services: ServiceMed[] = [];
    specialite: Specialite = {
      idSpecialite: 0,
      codeSpecialite: '',
      libSpecialite:''
    };
    service: ServiceMed = {
      idService: 0,
      codeService: '',
      libService:''
    };
  
 
    delegation :Delegation={
      idDelegation:1,
      codeDelegation:1,
      libDelegation:'',
      codeDuGouvernorat:1
    };
  
    selectedDelegation: any;
  constructor(private patientService: PatientService,private adminService:AdminService) { }
  ngOnInit(): void {
    this.adminService.getAllDelegations().subscribe(delegations => this.delegations = delegations);
    this.adminService.getAllSpecialite().subscribe(specialites => this.specialites =specialites);
    this.adminService.getAllServiceMedicales().subscribe(services=>this.services=services);
  //  this.adminService.getRDVsForPatient().subscribe((RDVs: RDV[]) => this.rdvs = RDVs);

   
  }
  choisirModePaiement(): void {
    this.patientService.choisirModePaiement(this.patient.cin, this.patient.modePaiement, this.patient.typeCaisse).subscribe(response => {
      console.log(response); // Gérez la réponse comme vous le souhaitez
    });
  }
  addPatient() {
    // Assuming you have a patient object ready to be added
    this.patientService.addPatient(this.patient).subscribe(
      (addedPatient: Patient) => {
        console.log('Patient added successfully:', addedPatient);
      },
      (error) => {
        console.error('Error adding Patient:', error);
      }
    );
  }
  
  updatePatient(): void {
    this.patientService.updatePatient(this.patient.cin, this.patient)
      .subscribe(
        (updatedPatientResponse) => {
          console.log('Patient updated successfully:', updatedPatientResponse);
          // You can perform additional actions upon successful update
        },
        (error) => {
          console.error('Error updating Patient:', error);
          // Handle the error, e.g., display an error message to the user
        }
      );
  }
  
  

  deletePatient() {
    this.patientService.deletePatient(this.patient.cin).subscribe(
      () => {
        console.log('Patient deleted successfully');
        // Additional logic if needed
      },
      (error) => {
        console.error('Error deleting Patient:', error);
      }
    );
  }
  
  /* onPieceJointeChange(event: any) {
    const file: File | null = event.target.files?.[0]; // Récupérer le premier fichier
  
    if (file) {
      const fileReader = new FileReader();
      fileReader.onload = () => {
        const pieceJointe: Document = {
          content: new Uint8Array(fileReader.result as ArrayBuffer),
          fileName: file.name,
          fileType: file.type,
        };
  
        this.patient.pieceJointe = pieceJointe;
      };
      fileReader.readAsArrayBuffer(file);
    }
  }
  
  onDossierMedicalChange(event: any) {
    const file: File | null = event.target.files?.[0]; // Récupérer le premier fichier
  
    if (file) {
      const fileReader = new FileReader();
      fileReader.onload = () => {
        const dossierMedical: Document = {
          content: new Uint8Array(fileReader.result as ArrayBuffer),
          fileName: file.name,
          fileType: file.type,
        };
  
        this.patient.dossierMedical = dossierMedical;
      };
      fileReader.readAsArrayBuffer(file);
    }
  } */
  
updateCard() {
  const firstGroup = (<HTMLInputElement>document.getElementById("first-group")).value || 'XXXX';
  const secondGroup = (<HTMLInputElement>document.getElementById("second-group")).value || 'XXXX';
  const thirdGroup = (<HTMLInputElement>document.getElementById("third-group")).value || 'XXXX';
  const fourthGroup = (<HTMLInputElement>document.getElementById("fourth-group")).value || 'XXXX';
  const expireDate = (<HTMLInputElement>document.getElementById("expireInput")).value;
  const cardName = (<HTMLInputElement>document.getElementById("nameInput")).value;



  const firstLi = document.getElementById("first-li");
  if (firstLi) {
    firstLi.textContent = firstGroup;
  }

  const secondLi = document.getElementById("second-li");
  if (secondLi) {
    secondLi.textContent = secondGroup;
  }

  const thirdLi = document.getElementById("third-li");
  if (thirdLi) {
    thirdLi.textContent = thirdGroup;
  }

  const lastLi = document.getElementById("last-li");
  if (lastLi) {
    lastLi.textContent = fourthGroup;
  }


  // Mise à jour de la date d'expiration
  const expireElement = document.querySelector('.Expire p');
  if (expireElement) {
      expireElement.textContent = expireDate;
  }

  // Mise à jour du nom sur la carte
  const nameElement = document.querySelector('.Name h3');
  if (nameElement) {
      nameElement.textContent = cardName;
  }
}

addRDV() {
  // Add RDV
  this.adminService.addRDV(this.rdv).subscribe(
    (addedRDV: RDV) => {
      console.log('RDV added successfully:', addedRDV);

      // Assign patient and medecin to RDV
      this.adminService.assignPatientAndMedecinTordv(this.rdv.nomDuPatient, this.rdv.nomDuMedecin, addedRDV).subscribe(
        () => {
          console.log('Association successful.');
          // Additional logic if needed
        },
        (error) => {
          console.error('Error associating RDV with Patient:', error);
        }
      );
    },
    (error) => {
      console.error('Error adding RDV:', error);
    }
  );
}
onDelegationChange(): void {
  console.log('Selected Delegation:', this.rdv.nomDelegation);
  // You can add more logic if needed
}


updateRDV() {
  // Assuming you have an RDV object ready to be updated
  this.adminService.updateRDV(this.rdv).subscribe(
    (updatedRDV: RDV) => {
      console.log('RDV updated successfully:', updatedRDV);
    },
    (error) => {
      console.error('Error updating RDV:', error);
    }
  );
}

deleteRDV(idRDV: number) {
  this.adminService.deleteRDV(idRDV).subscribe(
    () => {
      console.log('RDV deleted successfully');
    },
    (error) => {
      console.error('Error deleting RDV:', error);
    }
  );
}
searchMedecin(): void {
  const { delegationMedecin, libelleService, libelleSpecialite } = this.medecin;

  // Vérifiez que les valeurs sont définies avant d'effectuer la recherche
  if (delegationMedecin && libelleService && libelleSpecialite) {
    this.patientService.searchMedecin(delegationMedecin, libelleService, libelleSpecialite)
      .subscribe(response => {
        this.medecins = response;
      });
  } else {
    // Gérer le cas où l'une des valeurs est manquante
    console.error("Veuillez remplir tous les champs.");
  }
}
loadRendezVousPasses(cinPatient: number): void {
  this.patientService.getRendezVousPassesPourPatient(cinPatient)
    .subscribe(
      (response: RDV[]) => {
        this.rdvPasses = response;
      },
      (error) => {
        if (error.status === 403) {
          console.error('Access Forbidden. Check user permissions.');
        } else if (error.status === 404) {
          console.error('Patient not found or no past appointments available.');
        } else {
          console.error('Error loading past appointments:', error);
        }
      }
    );
}

loadRendezVousAVenir(cinPatient: number): void {
  this.patientService.getRendezVousAVenirPourPatient(cinPatient)
    .subscribe(
      (response: RDV[]) => {
        this.rdvAVenir = response;
      },
      (error: any) => {
        if (error.status === 403) {
          console.error('Access Forbidden. Check user permissions.');
        } else if (error.status === 404) {
          console.error('Patient not found or no upcoming appointments available.');
        } else {
          console.error('Error fetching upcoming appointments:', error);
        }
      }
    );
}


onFileSelected(event: any): void {
  const file: File = event.target.files[0];

  if (file) {
    const patientCIN = this.patient.cin; // Assuming you have a selected patient
    this.patientService.addDocument(file, patientCIN).subscribe(
      response => {
        console.log(response);
        // Optionally, handle success (e.g., show a success message)
      },
      error => {
        console.error(error);
        // Optionally, handle error (e.g., show an error message)
      }
    );
  }
}

downloadDocument(patientCIN: number): void {
  this.patientService.getDocument(patientCIN).subscribe(
    response => {
      const blob = new Blob([response], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      window.open(url);
    },
    error => {
      console.error(error);
    }
  );
}
getRDVsForPatient(): void {
  this.patientService.getRDVsForPatient(this.patient.cin).subscribe(
    (rdvs: RDV[]) => {
      this.rdvs = rdvs;
    },
    error => {
      console.error('Une erreur s\'est produite : ', error);
   
    }
  );
} 
getAllRDVs() {
  this.adminService.getAllRDVs().subscribe(
    (rdvs: RDV[]) => {
      this.rdvs = rdvs;
    },
    (error: any) => {
      console.error('Error fetching RDVs:', error);
    }
  );
}
getEtatRDVClass(etatRDV: number): string {
  switch (etatRDV) {
    case 0:
      return 'status active';
    case 1:
      return 'status cancelled';
    case 2:
      return 'status instant';
    case 3:
      return 'status disabled';
    default:
      return '';
  }
}
getEtatRDVChoice(etatRDV: number): string {
  return 'choix' + etatRDV;
}

getTypeOfEtatRDV(): string {
  return typeof this.rdv.etatRDV;
}
}