<?php

namespace BaskelBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * PanierLigne
 *
 * @ORM\Table(name="panier_ligne")
 * @ORM\Entity(repositoryClass="BaskelBundle\Repository\PanierLigneRepository")
 */
class PanierLigne
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity="BaskelBundle\Entity\Produit")
     * @ORM\JoinColumn(name="id_produit", referencedColumnName="id_produit")
     */
    private $idProduit;



    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer")
     */
    private $quantite;
    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User")
     * @ORM\JoinColumn(name="idUser", referencedColumnName="id")
     */
    private $idUser;

    /**
     * @ORM\ManyToOne(targetEntity="BaskelBundle\Entity\Commande")
     * @ORM\JoinColumn(name="idCommande", referencedColumnName="id")
     */
    private $idC;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idProduit
     *
     * @param integer $idProduit
     *
     * @return PanierLigne
     */
    public function setIdProduit($idProduit)
    {
        $this->idProduit = $idProduit;

        return $this;
    }

    /**
     * Get idProduit
     *
     * @return int
     */
    public function getIdProduit()
    {
        return $this->idProduit;
    }

    /**
     * Set quantite
     *
     * @param integer $quantite
     *
     * @return PanierLigne
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;

        return $this;
    }

    /**
     * Get quantite
     *
     * @return int
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * Set idUser
     *
     * @param integer $idUser
     *
     * @return PanierLigne
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return int
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * Set idC
     *
     * @param integer $idC
     *
     * @return PanierLigne
     */
    public function setIdC($idC)
    {
        $this->idC = $idC;

        return $this;
    }

    /**
     * Get idC
     *
     * @return int
     */
    public function getIdC()
    {
        return $this->idC;
    }
}

