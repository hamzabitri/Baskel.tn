<?php

namespace BaskelBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Wish
 *
 * @ORM\Table(name="wish")
 * @ORM\Entity(repositoryClass="BaskelBundle\Repository\WishRepository")
 */
class Wish
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
     * @ORM\JoinColumn(name="idProduit", referencedColumnName="id_produit")
     */
    private $idProduit;

    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User")
     * @ORM\JoinColumn(name="idUser", referencedColumnName="id")
     */
    private $idUser;


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
     * @return Wish
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
     * Set idUser
     *
     * @param integer $idUser
     *
     * @return Wish
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
}

